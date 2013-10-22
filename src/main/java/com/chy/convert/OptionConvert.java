package com.chy.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chy.model.Option;
import com.chy.service.OptionService;

@Component
@FacesConverter("com.chy.OptionConvert")
public class OptionConvert
  implements Converter
{

  @Autowired
  OptionService service;

  public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue)
  {
    if (submittedValue.trim().equals("")) {
      return null;
    }
    if (this.service == null) {
      this.service = ((OptionService)facesContext.getApplication().evaluateExpressionGet(facesContext, "#{optionService}", OptionService.class));
    }

    return this.service.get(submittedValue);
  }

  public String getAsString(FacesContext facesContext, UIComponent UIComponent, Object value)
  {
    if ((value == null) || (value.equals(""))) {
      return "";
    }
    return ((Option)value).getId();
  }
}