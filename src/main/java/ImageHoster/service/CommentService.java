package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import ImageHoster.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    //The method calls the addComment() method in the Repository and passes the Comment to be updated in the database
    public void addComment(Comment comment) { commentRepository.addComment(comment); }
}