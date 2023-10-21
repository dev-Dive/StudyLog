package com.devdive.backend.post.application.port.in;

import com.devdive.backend.common.SelfValidating;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class PostCreateRequestApplicationDto extends SelfValidating<PostCreateRequestApplicationDto> {

    @NotNull
    private final Long memberId;

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

    PostCreateRequestApplicationDto(Long memberId,
                                           Long studyId,
                                           String thumbnailUrl,
                                           String title,
                                           String subtitle,
                                           String content,
                                            List<String> tags) {
        this.memberId = memberId;
        this.studyId = studyId;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.tags = tags;

        this.validateSelf();;
    }

    public static PostCreateRequestDtoBuilder builder(){
        return new PostCreateRequestDtoBuilder();
    }

    public static class PostCreateRequestDtoBuilder{
        private Long memberId;
        private Long studyId;
        private String thumbnailUrl;
        private String title;
        private String subtitle;
        private String content;
        private List<String> tags;

        public PostCreateRequestDtoBuilder memberId(Long memberId){
            this.memberId = memberId;
            return this;
        }
        public PostCreateRequestDtoBuilder studyId(Long studyId){
            this.studyId = studyId;
            return this;
        }
        public PostCreateRequestDtoBuilder thumbnailUrl(String thumbnailUrl){
            this.thumbnailUrl = thumbnailUrl;
            return this;
        }
        public PostCreateRequestDtoBuilder title(String title){
            this.title = title;
            return this;
        }
        public PostCreateRequestDtoBuilder subtitle(String subtitle){
            this.subtitle = subtitle;
            return this;
        }
        public PostCreateRequestDtoBuilder content(String content){
            this.content = content;
            return this;
        }
        public PostCreateRequestDtoBuilder tags(List<String> tags){
            this.tags = tags;
            return this;
        }

        public PostCreateRequestApplicationDto build(){
            return new PostCreateRequestApplicationDto(memberId, studyId, thumbnailUrl, title, subtitle, content, tags);
        }
    }

}
