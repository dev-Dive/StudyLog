package com.devdive.backend.post.application.port.out;

import com.devdive.backend.post.application.port.in.PostViewApplicationDto;

public interface LoadPostPort {

    PostViewApplicationDto viewPost(Long postId);


}
