package Shop.service;

import Shop.PostDto;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Long save(PostDto dto) {
        Post post = new Post(dto.getTitle(), dto.getContent());
        return postRepository.save(post).getId();
    }

    public Post find(Long id) {
        return postRepository.findById(id).orElseThrow();
    }

}