package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    /*
    This controller method is called when the request pattern is of type '/image/{imageId}/{imageTitle}/comments' and also the incoming request is of POST type
    The method receives all the details of the Comments to be stored in the database, and now the comment will be sent to the business logic to be persisted in the database
    Set the date on which the comment is posted
    After storing the image, this method directs to displaying that image again
     */
    @RequestMapping(value="/image/{imageId}/{imageTitle}/comments" , method = RequestMethod.POST)
    public String addComment(@RequestParam("comment") String file, @PathVariable("imageId") Integer id, @PathVariable("imageTitle") String title, HttpSession session, Model model) throws IOException {
        Image image = imageService.getImageById(id);
        User user = (User) session.getAttribute("loggeduser");
        Comment newComment = new Comment();
        newComment.setUser(user);
        newComment.setCreatedDate(new Date());
        newComment.setText(file);
        newComment.setImage(image);
        commentService.addComment(newComment);
        return "redirect:/images/"+image.getId()+"/"+image.getTitle();
    }
}