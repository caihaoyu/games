package com.chy.view.bean.round;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.chy.model.Round;
import com.chy.service.RoundService;
import com.chy.view.bean.BaseBean;

@Component
@Scope("view")
public class RoundListBean extends BaseBean
{
  private static final long serialVersionUID = -4421544820913147749L;

  @Autowired
  private RoundService service;
  private List<Round> roundList;
  private Round selectedRound;

  @PostConstruct
  public void setup()
  {
    this.roundList = this.service.findAllRound();
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

    if (null != externalContext.getRequestParameterMap().get("editError"))
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "������������", "������������������������"));
  }

  public void confirmDelete()
  {
    this.selectedRound = ((Round)getDataTable("roundList").getRowData());
    RequestContext rc = RequestContext.getCurrentInstance();
    rc.execute("$('#confirmDeleteDlg').modal();");
  }

  public void delete()
  {
    this.service.deleteRound(this.selectedRound);
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "info", this.selectedRound.getAnswer() + "������������"));

    this.roundList.remove(this.selectedRound);
    this.selectedRound = null;
    RequestContext rc = RequestContext.getCurrentInstance();
    rc.execute("$('#confirmDeleteDlg').modal('hide');");
  }

  public void goToEdit() {
    this.selectedRound = ((Round)getDataTable("roundList").getRowData());
    try {
      FacesContext.getCurrentInstance().getExternalContext().redirect("/games/xhtml/round/edit/editRound.xhtml?faces-redirect=true&mode=edit&roundId=" + this.selectedRound.getId());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public List<Round> getRoundList() {
    return this.roundList;
  }

  public void setRoundList(List<Round> roundList) {
    this.roundList = roundList;
  }

  public Round getSelectedRound() {
    return this.selectedRound;
  }

  public void setSelectedRound(Round selectedRound) {
    this.selectedRound = selectedRound;
  }
}