package com.codegym.dating.service.impl;

import com.codegym.dating.config.MailConfig;
import com.codegym.dating.dto.UserDto;
import com.codegym.dating.model.Account;
import com.codegym.dating.model.TypeUser;
import com.codegym.dating.model.User;
import com.codegym.dating.payload.request.UpdateStatusRequest;
import com.codegym.dating.repository.IAccountRepository;
import com.codegym.dating.repository.ITypeUserRepository;
import com.codegym.dating.repository.IUserRepository;
import com.codegym.dating.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private ITypeUserRepository iTypeUserRepository;

    @Autowired
    private IAccountRepository iAccountRepository;

    @Override
    public Optional<UserDto> findByIdDto(Integer id) {
        return this.iUserRepository.findByIdDto(id);
    }

//    public Page<UserDto> findAllPage(Pageable pageable) {
//        return iUserRepository.getAllPage(pageable);
//    }

    @Override
    public Page<UserDto> userPage(String name, String dateOfBirth, String address, String job, String
            gender, String hobbitName, Pageable pageable) {
        if (gender == "") {
            return iUserRepository.findAllUserAndSearch
                    ("%" + name + "%",
                            dateOfBirth,
                            "%" + address + "%",
                            "%" + job + "%",
                            0,
                            1,
                            "%" + hobbitName + "%",
                            pageable);
        } else if (gender.equals("0")) {
            return iUserRepository.findAllUserAndSearch
                    ("%" + name + "%",
                            dateOfBirth,
                            "%" + address + "%",
                            "%" + job + "%",
                            0,
                            0,
                            "%" + hobbitName + "%",
                            pageable);
        } else {
            return iUserRepository.findAllUserAndSearch
                    ("%" + name + "%",
                            dateOfBirth,
                            "%" + address + "%",
                            "%" + job + "%",
                            1,
                            1,
                            "%" + hobbitName + "%",
                            pageable);
        }
    }

    @Override
    public Page<UserDto> findAllSearchPage(Pageable pageable, String name) {
        return iUserRepository.getAllSearchPage(pageable, '%' + name + '%');
    }

    @Override
    public User findUserById(Integer id) {
        return iUserRepository.findByIdNativeQuery(id);
//        return iUserRepository.getAllSearchPage(pageable, '%' + name + '%');
    }

    @Override
    public void updateCoin(Integer coin, Integer idUser) {
        iUserRepository.updateCoin(coin, idUser);
    }

    @Override
    public void updateTypeUser(Integer coin, Integer idUser) {
        Integer idTypeUser = 0;
        if (coin >= 0 && coin < 100) {
            idTypeUser = 1;
        } else if (coin >= 100 && coin < 1000) {
            idTypeUser = 2;
        } else if (coin >= 1000 && coin < 5000) {
            idTypeUser = 3;
        } else if (coin >= 5000) {
            idTypeUser = 4;
        }
        this.iUserRepository.updateTypeUser(idTypeUser, idUser);
    }

    @Override
    public Page<UserDto> findAllPage(Pageable pageable) {
//        return iUserRepository.getAllSearchPage(pageable);
        return null;
    }

    @Override
    public List<UserDto> findAllSearch(String name) {
        return iUserRepository.getAllSearch(name);
    }

//    -------------

    @Override
    public void updateAvatar(User user) {
        this.iUserRepository.updateAvatar(user.getAvatar(), user.getIdUser());
    }

    @Override
    public void updateStatusActive(User user) {
        this.iUserRepository.updateStatusActive(user.getStatusActive().getId(), user.getIdUser());
        System.out.println(user.getIdUser());
    }

    @Override
    public Page<User> findAll(String name, Pageable pageable) {
        return iUserRepository.findByNameContaining(name, pageable);
    }

    @Override
    public Page<User> findByTypeUser(String name, String typeUser, Pageable pageable) {
        return iUserRepository.findByTypeUser(name, typeUser, pageable);
    }

    @Override
    public Optional<User> findByIdUser(int id) {
        return iUserRepository.findById(id);
    }

    @Override
    public void updateWarningUser(UpdateStatusRequest updateStatusRequest) {
        Optional<User> userOpt = this.findByIdUser(updateStatusRequest.getIdUser());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.getAccount().setStatus(updateStatusRequest.getStatus());
            iUserRepository.save(user);

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", MailConfig.HOST_NAME);
            props.put("mail.smtp.socketFactory.port", MailConfig.SSL_PORT);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.port", MailConfig.SSL_PORT);

            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD);
                }
            });

            Message message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress(MailConfig.APP_EMAIL));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getAccount().getEmail()));
                message.setSubject("Cảnh cáo thành viên");

                Multipart multipart = new MimeMultipart();
                MimeBodyPart textBodyPart = new MimeBodyPart();
                String htmlContent = "<body style=\"margin: 0 10px;font-family: system-ui;\n" +
                        "    font-weight: 500;\">\n" +
                        "<div style=\"width: 100%;height: 800px;background-color: indianred;display: flex;align-items: center;\">\n" +
                        "  <div style=\"width: 700px;height: 700px;margin: 0 auto;background-color: white;display: flex;\n" +
                        "  align-items: center;border-radius: 5px\">\n" +
                        "    <div style=\"width: 500px;margin: 20px auto;border-radius: 5px;\">\n" +
                        "      <h3>Kính gửi quý khách!</h3>\n" +
                        "      <p>Gần đây chúng tôi nhận được nhiều phản ánh từ hệ thống tố cáo tài khoản sử dụng của bạn vi phạm điều lệ của\n" +
                        "        trang web nhiều lần.\n" +
                        "        .Qua xác minh chúng tôi quyết định khóa account theo thời gian quy định.\n" +
                        "        Nếu bạn có bất kì thắc mắc hay khiếu nại gì thì bạn vui lòng liên hệ đội ngũ suport để xử lý!</p>\n" +
                        "      <img style=\"width: 500px;height: 200px;\" src=\"https://modiaz.com/upload/2020/08/tam-anh-huong-cua-phim-tu-gioi-thieu-doanh-nghiep-trong-truyen-thong-noi-bo-xay-dung-van-hoa-doanh-nghiep.jpg\" alt=\"\">\n" +
                        "\n" +
                        "      <p>Trân trọng!</p>\n" +
                        "      <h4 style=\"color: red\">Đội ngũ Webdating</h4>\n" +
                        "      <p>Thư này được gửi tự động từ địa chỉ của hệ thống.Vui lòng không phản hồi!</p>\n" +
                        "      <p>Mail suport: <span style=\"color: #1943c2\">tuannc1004@gmail.com</span></p>\n" +
                        "      <p>Hotmail: <span style=\"color: #1943c2\">0344848457</span></p>\n" +
                        "      <p>Website:<a style=\"color: #1943c2\" href=\" webdating.com\" style=\"color: #e3274c\">\n" +
                        "        webdating.com\n" +
                        "      </a></p>\n" +
                        "    </div>\n" +
                        "  </div>\n" +
                        "</div>\n" +
                        "</body>";
                textBodyPart.setContent(htmlContent, "text/html;charset=UTF-8");

                multipart.addBodyPart(textBodyPart);

                message.setContent(multipart);

                Transport.send(message);
                System.out.println("sending");
            } catch (MessagingException ex) {
                ex.printStackTrace();
                System.out.println("send Errors!!!!!!");
            }
        }
    }


    @Transactional
    @Override
    public void updateUser(User user) {
        TypeUser typeUser = this.iTypeUserRepository.findTypeUserById(1);

        user.setTypeUser(typeUser);

        user.setCoin(0);

        user.setJoinDay(LocalDate.now());

        if (user.getAvatar().isEmpty()) {
            user.setAvatar("https://scienceoxford.com/wp-content/uploads/2018/03/avatar-male.jpg");
        }

        Account account = iAccountRepository.findAccountByIdUser(user.getIdUser());
        account.setStatus(1);
        this.iUserRepository.save(user);
    }

    @Override
    public void saveUser(User user) {
        this.iUserRepository.save(user);
    }

    @Override
    public User getUserByIdAccount(Integer idAccount) {
        return iUserRepository.findByAccount_IdAccount(idAccount);
    }


    @Override
    public User findById(Integer id) {
        User user = this.iUserRepository.findById(id).get();
        return user;
    }

}
