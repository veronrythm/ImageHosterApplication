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
        List<Comment> commentList = image.getComments();
        commentList.add(newComment);

        model.addAttribute("comments",commentList);
        model.addAttribute("image",image);
        model.addAttribute("tags",image.getTags());
        return "images/image";
    }
}