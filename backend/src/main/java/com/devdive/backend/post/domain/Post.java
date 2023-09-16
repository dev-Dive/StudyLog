package com.devdive.backend.post.domain;

import java.util.List;

public class Post {

  Long studyId;
  String thumbnail_url;
  String title;
  String subtitle;
  String content;
  List<String> tag;
  Member member;
  List<Reply> replies;
}
