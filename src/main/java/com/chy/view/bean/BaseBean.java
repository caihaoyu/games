package com.chy.view.bean;

import java.io.Serializable;
import java.util.Iterator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.primefaces.component.datatable.DataTable;

public class BaseBean
  implements Serializable
{
  private static final long serialVersionUID = 1L;

  public UIComponent findComponent(UIComponent c, String id)
  {
    if (id.equals(c.getId())) {
      return c;
    }
    Iterator<UIComponent> kids = c.getFacetsAndChildren();
    while (kids.hasNext()) {
      UIComponent found = findComponent((UIComponent)kids.next(), id);
      if (found != null) {
        return found;
      }
    }
    return null; }

  public DataTable getDataTable(String id) {
    return ((DataTable)findComponent(FacesContext.getCurrentInstance().getViewRoot(), id));
  }
}