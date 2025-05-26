package Shop.controller;

import Shop.PostDto;
import Shop.service.PostService;

@Controller
@RequiredArgsConstructor
public class PostWebController {

    private final PostService postService;

    @GetMapping("/posts/new")
    public String newPostForm(Model model) {
        model.addAttribute("postDto", new PostDto());
        return "post/new";
    }

    @PostMapping("/posts")
    public String save(@ModelAttribute PostDto dto) {
        postService.save(dto);
        return "redirect:/";
    }
}