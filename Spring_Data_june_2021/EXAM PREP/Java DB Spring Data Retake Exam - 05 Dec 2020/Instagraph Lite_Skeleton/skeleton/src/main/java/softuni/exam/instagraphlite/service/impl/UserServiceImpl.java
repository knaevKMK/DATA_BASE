package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.constatnt.Static;
import softuni.exam.instagraphlite.models.dto.json.input.UserJsonDto;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.models.entities.User;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.FileUtil;
import softuni.exam.instagraphlite.util.ValidatorUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PictureService pictureService;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidatorUtil validatorUtil;

    public UserServiceImpl(UserRepository userRepository, PictureService pictureService, FileUtil fileUtil, ModelMapper modelMapper, Gson gson, ValidatorUtil validatorUtil) {
        this.userRepository = userRepository;
        this.pictureService = pictureService;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public Boolean аreImported() {
        return userRepository.count() != 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return fileUtil.content(Static.USER_FILEPATH, "");
    }

    @Override
    public String importUsers() throws IOException {
        List<String> report = new ArrayList<>();

        Arrays.stream(gson.fromJson(readFromFileContent(), UserJsonDto[].class))
                .forEach(userDto -> {


                    try {
                        if (!validatorUtil.isValid(userDto)) {
                            throw new Exception();
                        }
                        Picture picture = pictureService.getPictureByPath(userDto.getProfilePicture())
                                .orElseThrow(Exception::new);

                        User user = modelMapper.map(userDto, User.class);
                        user.setProfilePicture(picture);

                        userRepository.save(user);
                        report.add("Successfully imported User: " + user.getUsername());
                    } catch (Exception e) {
                        report.add("Invalid User");
                    }
                });

        return String.join(System.lineSeparator(), report);

    }

    @Override
    public String exportUsersWithTheirPosts() {
        return userRepository.findAllOrderByPostSizeThenByUserId()
                .stream().map(user ->
                        String.format("User: %s%nPost count: %d%n==Post Details:%n%s"
                                , user.getUsername()
                                , user.getPosts().size()
                                , user.getPosts().stream()
                                        .sorted(( b,a) -> Double.compare(b.getPicture().getSize(), a.getPicture().getSize()))
                                        .map(post ->
                                                String.format("----Caption: %s%n----Picture Size: %.2f"
                                                        , post.getCaption(), post.getPicture().getSize()))
                                        .collect(Collectors.joining(System.lineSeparator()))))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
