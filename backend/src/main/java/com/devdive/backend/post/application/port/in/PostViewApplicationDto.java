package com.devdive.backend.post.application.port.in;

import com.devdive.backend.common.SelfValidating;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class PostViewApplicationDto extends SelfValidating<PostViewApplicationDto> {

    @NotNull
    private final Long postId;

    @NotNull
    private final Long studyId;

    @NotNull
    private final String thumbnailUrl;

    @NotNull
    private final String title;

    private final String subtitle;
    @NotNull
    private final String content;

    @NotNull
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private final List<String> tags;

    PostViewApplicationDto(Long postId,
                           Long studyId,
                           String thumbnailUrl,
                           String title,
                           String subtitle,
                           String content,
                           List<String> tags) {
        this.postId = postId;
        this.studyId = studyId;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.tags = tags;

        this.validateSelf();;
    }

    public static PostViewApplicationDtoBuilder builder(){
        return new PostViewApplicationDtoBuilder();
    }

    public static class PostViewApplicationDtoBuilder{
        private Long postId;
        private Long studyId;
        private String thumbnailUrl;
        private String title;
        private String subtitle;
        private String content;
        private List<String> tags;

        public PostViewApplicationDtoBuilder postId(Long postId){
            this.postId = postId;
            return this;
        }
        public PostViewApplicationDtoBuilder studyId(Long studyId){
            this.studyId = studyId;
            return this;
        }
        public PostViewApplicationDtoBuilder thumbnailUrl(String thumbnailUrl){
            this.thumbnailUrl = thumbnailUrl;
            return this;
        }
        public PostViewApplicationDtoBuilder title(String title){
            this.title = title;
            return this;
        }
        public PostViewApplicationDtoBuilder subtitle(String subtitle){
            this.subtitle = subtitle;
            return this;
        }
        public PostViewApplicationDtoBuilder content(String content){
            this.content = content;
            return this;
        }
        public PostViewApplicationDtoBuilder tags(List<String> tags){
            this.tags = tags;
            return this;
        }

        public PostViewApplicationDto build(){
            return new PostViewApplicationDto(postId, studyId, thumbnailUrl, title, subtitle, content, tags);
        }
    }
}
