package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.constatnt.Static;
import softuni.exam.instagraphlite.models.dto.xml.input.PostRootXmlDto;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.models.entities.Post;
import softuni.exam.instagraphlite.models.entities.User;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.FileUtil;
import softuni.exam.instagraphlite.util.ValidatorUtil;
import softuni.exam.instagraphlite.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final PictureService pictureService;
    private final XmlParser xmlParser;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    public PostServiceImpl(PostRepository postRepository, UserService userService, PictureService pictureService, XmlParser xmlParser, FileUtil fileUtil, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.pictureService = pictureService;
        this.xmlParser = xmlParser;

        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public Boolean аreImported() {
        return postRepository.count() != 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return fileUtil.content(Static.POST_FILEPATH, "");
    }

    @Override
    public String importPosts() throws IOException, JAXBException {
        List<String> report = new ArrayList<>();

        xmlParser.importXMl(PostRootXmlDto.class, Static.POST_FILEPATH)
                .getPostRootXmlDtos()
                .forEach(postDto -> {

                    try {
                        if (!validatorUtil.isValid(postDto)) {
                            throw new Exception();
                        }
                        User user = userService.findByUsername(postDto.getUser().getUsername())
                                .orElseThrow(Exception::new);
                        Picture picture = pictureService.getPictureByPath(postDto.getPicture().getPath())
                                .orElseThrow(Exception::new);

                        Post post = modelMapper.map(postDto, Post.class);
                        post.setPicture(picture)
                                .setUser(user);
                        postRepository.save(post);
                        report.add("Successfully imported Post, made by " + postDto.getUser().getUsername());
                    } catch (Exception e) {
                        report.add("Invalid Post");
                    }
                });
        return String.join(System.lineSeparator(), report);
    }
}
