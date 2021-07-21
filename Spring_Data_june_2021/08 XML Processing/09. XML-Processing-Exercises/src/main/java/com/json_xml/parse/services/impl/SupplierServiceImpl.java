package com.json_xml.parse.services.impl;

import com.json_xml.parse.models.dto.partCarSale.input.SupplierRootDto;
import com.json_xml.parse.models.dto.partCarSale.out.SupplierRootOutDto;
import com.json_xml.parse.models.dto.partCarSale.out.SuppliersIdNamePartCountDTO;
import com.json_xml.parse.models.entities.partCarSale.SupplierEntity;
import com.json_xml.parse.repositories.SupplierRepository;
import com.json_xml.parse.services.SupplierService;
import com.json_xml.parse.util.IOUtil;
import com.json_xml.parse.util.ValidationUtil;
import com.json_xml.parse.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.json_xml.parse.constants.Paths.*;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final IOUtil ioUtil;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, IOUtil ioUtil) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.ioUtil = ioUtil;
    }

    @Override
    public void seedData() throws IOException, JAXBException {
        if (supplierRepository.count() != 0) {
            ioUtil.print("suppliers_table data is not empty");
            return;
        }

        ioUtil.print("Data will seed from " + SUPPLIER_XML_FILEPATH + "\nPLEASE WAIT...");

        String content = String.join("", ioUtil.readFile(SUPPLIER_XML_FILEPATH));

        List<String> report = new ArrayList<>();

        xmlParser.importXMl(SupplierRootDto.class, SUPPLIER_XML_FILEPATH)
                .getSupplierDtos()
                .forEach(supplierDto -> {
                    try {
                        if (!validationUtil.isValid(supplierDto)) {
                            throw new Exception("Invalid supplier: " + supplierDto.getName() + "\n" +
                                    validationUtil.violation(supplierDto).stream().map(ConstraintViolation::getMessage)
                                            .collect(Collectors.joining(System.lineSeparator())));

                        }
                        SupplierEntity supplier = modelMapper.map(supplierDto, SupplierEntity.class);

                        supplierRepository.save(supplier);
                        report.add("Success add suplier: " + supplier.getName());
                    } catch (Exception e) {
                        report.add(e.getMessage());
                    }
                });
        ioUtil.print("\nReport:\n" + String.join(System.lineSeparator(), report) + "\nCompleted\n");
    }

    @Override
    public SupplierEntity getRandomById() {
        return supplierRepository.getById(ThreadLocalRandom.current().nextLong(1, supplierRepository.count() + 1));

    }

    @Override
    public void getLocal() throws JAXBException {

        SupplierRootOutDto supplierRootOutDto = new SupplierRootOutDto();

        supplierRootOutDto.setSuppliersDtos(supplierRepository.findAllByImporterIsFalse()
                .stream().map(supplier -> {
                    SuppliersIdNamePartCountDTO supplierView =
                            modelMapper.map(supplier, SuppliersIdNamePartCountDTO.class);
                    supplierView.setPartsCount(supplier.getParts().size());
                    return supplierView;
                })
                .collect(Collectors.toSet()));
        xmlParser.exportXML(supplierRootOutDto,OUT_DIR_XML_FILEPATH+OUT_SUPPLIER_LOCAL_FILE);
    }
}
