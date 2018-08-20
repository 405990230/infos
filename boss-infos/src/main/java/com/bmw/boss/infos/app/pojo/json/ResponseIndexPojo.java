package com.bmw.boss.infos.app.pojo.json;

import java.io.Serializable;

public class ResponseIndexPojo implements Serializable{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String type;
  private String color;
  private String description;
  
  private String fixString(String o) {

      StringBuilder sb = new StringBuilder();
      if (o != null && o.length() > 1) {
          for (int i = 0; i < o.length(); i++) {
              char currentChar = o.charAt(i);
              if (i > 0 && currentChar >= 'A' && currentChar <= 'Z') {
                  sb.append(" ");
                  sb.append((char) (currentChar + 32));

              } else {
                  sb.append(currentChar);
              }

          }
          return sb.toString();
      } else {
          return o;
      }
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = fixString(description);
  }
}
