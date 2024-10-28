package com.example.springdonateweb.Controllers;

import com.example.springdonateweb.Models.Dtos.Comments.CommentAddDto;
import com.example.springdonateweb.Models.Dtos.Comments.CommentCreateDto;
import com.example.springdonateweb.Models.Dtos.Comments.CommentDto;
import com.example.springdonateweb.Models.Dtos.Comments.CommentUpdateDto;
import com.example.springdonateweb.Services.interfaces.ICommentsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/comments")
public class CommentsController {

    private final ICommentsService commentsService;

    @GetMapping("")
    public String listComments(Model model) {
        List<CommentDto> comments = commentsService.findAll();
        model.addAttribute("comments", comments);
        return "comments/list";
    }

    @GetMapping("/create")
    public String createCommentForm(Model model) {
        model.addAttribute("comment", new CommentCreateDto());
        return "comments/create";
    }

    @PostMapping("/create")
    public String createComment(
            @Valid @ModelAttribute("comment") CommentCreateDto commentCreateDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "comments/create";
        }
        commentsService.create(commentCreateDto);
        redirectAttributes.addFlashAttribute("success", "Comment created successfully");
        return "redirect:/admin/comments";
    }

    @GetMapping("/edit/{id}")
    public String editCommentForm(@PathVariable int id, Model model) {
        CommentDto comment = commentsService.findById(id);
        if (comment == null) {
            return "redirect:/admin/comments";
        }
        model.addAttribute("comment", comment);
        return "comments/edit";
    }

    @PostMapping("/edit/{id}")
    public String editComment(
            @PathVariable int id,
            @Valid @ModelAttribute("comment") CommentUpdateDto commentUpdateDto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "comments/edit";
        }
        commentsService.update(commentUpdateDto);
        redirectAttributes.addFlashAttribute("success", "Comment updated successfully");
        return "redirect:/admin/comments";
    }

    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable int id, RedirectAttributes redirectAttributes) {
        commentsService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Comment deleted successfully");
        return "redirect:/admin/comments";
    }
}
