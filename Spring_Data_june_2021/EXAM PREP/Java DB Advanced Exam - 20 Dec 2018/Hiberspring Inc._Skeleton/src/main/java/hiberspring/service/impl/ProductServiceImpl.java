package hiberspring.service.impl;

import hiberspring.domain.dtos.ProductRootDto;
import hiberspring.domain.entities.BranchEntity;
import hiberspring.domain.entities.ProductEntity;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.ProductRepository;
import hiberspring.service.ProductService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static hiberspring.common.Constants.*;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public ProductServiceImpl(ProductRepository productRepository, BranchRepository branchRepository, FileUtil fileUtil, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean productsAreImported() {
        return productRepository.count() != 0;
    }

    @Override
    public String readProductsXmlFile() throws IOException {
        return String.join("", fileUtil.readFile(DIR_PATH_TO_FILES + "products.xml"));
    }

    @Override
    public String importProducts() throws JAXBException {
        List<String> report = new ArrayList<>();


        ProductRootDto productRootDto =
                xmlParser.parseXml(ProductRootDto.class, "src/main/resources/files/products.xml");
        productRootDto.getProductsDto()
                .forEach(productDto -> {
                    try {
                        BranchEntity branch = branchRepository.findByName(productDto.getBranchName())
                                .orElseThrow(() -> new Exception());
                        if (!validationUtil.isValid(productDto)) {
                            throw new Exception();
                        }
                        ProductEntity product = modelMapper.map(productDto, ProductEntity.class);
                        product.setBranch(branch);

                        productRepository.save(product);

                        report.add(String.format(SUCCESSFUL_IMPORT_MESSAGE, product.getClass().getSimpleName(),
                                product.getName()));
                    } catch (Exception e) {
                        report.add(INCORRECT_DATA_MESSAGE);
                    }
                });


        return String.join(System.lineSeparator(), report);
    }
}
