package com.ssafy.vo;

public class Guide {
   private int guide_id;
   private String guide_name;
   private String guide_title;
   private String guide_type;
   private int guide_like;
   private String guide_movie;
   private String guide_date;
   private String guide_script;

   public Guide() {
      super();
   }

   public Guide(int guide_id, String guide_name, String guide_title, String guide_type, int guide_like, String guide_movie,
         String guide_date, String guide_script) {
      super();
      this.guide_id = guide_id;
      this.guide_name = guide_name;
      this.guide_title = guide_title;
      this.guide_type = guide_type;
      this.guide_like = guide_like;
      this.guide_movie = guide_movie;
      this.guide_date = guide_date;
      this.guide_script = guide_script;
   }

   public int getGuide_id() {
      return guide_id;
   }

   public void setGuide_id(int guide_id) {
      this.guide_id = guide_id;
   }

   public String getGuide_name() {
      return guide_name;
   }

   public void setGuide_name(String guide_name) {
      this.guide_name = guide_name;
   }

   public String getGuide_title() {
      return guide_title;
   }

   public void setGuide_title(String guide_title) {
      this.guide_title = guide_title;
   }

   public String getGuide_type() {
      return guide_type;
   }

   public void setGuide_type(String guide_type) {
      this.guide_type = guide_type;
   }

   public int getGuide_like() {
      return guide_like;
   }

   public void setGuide_like(int guide_like) {
      this.guide_like = guide_like;
   }

   public String getGuide_movie() {
      return guide_movie;
   }

   public void setGuide_movie(String guide_movie) {
      this.guide_movie = guide_movie;
   }

   public String getGuide_date() {
      return guide_date;
   }

   public void setGuide_date(String guide_date) {
      this.guide_date = guide_date;
   }

   public String getGuide_script() {
      return guide_script;
   }

   public void setGuide_script(String guide_script) {
      this.guide_script = guide_script;
   }



   
   
}