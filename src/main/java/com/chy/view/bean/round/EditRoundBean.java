package com.chy.view.bean.round;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.chy.model.Image;
import com.chy.model.Option;
import com.chy.model.Round;
import com.chy.service.ImageService;
import com.chy.service.OptionService;
import com.chy.service.RoundService;
import com.chy.util.LanguageUtil;
import com.chy.util.UploadUtil;
import com.chy.view.bean.BaseBean;

@Component
@Scope("view")
public class EditRoundBean extends BaseBean
{
  private static final long serialVersionUID = 5650270406663031229L;

  @Autowired
  private ImageService imageService;

  @Autowired
  private RoundService service;

  @Autowired
  private OptionService optionService;
  private List<Option> selectedOptions=new ArrayList<Option>();
  private Round round;
  private Set<String> selectedOptionIds=new HashSet<String>();
  private String newOptionName;
  private boolean showImage;
  private List<Image> images=new ArrayList<Image>();
  private String deleteImageId;
  private boolean edit;



  @PostConstruct
  public void setup()
  {
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

    if ((externalContext.getRequestParameterMap().get("mode") != null) && (((String)externalContext.getRequestParameterMap().get("mode")).equals("edit")))
    {
      this.edit = true;
      if (externalContext.getRequestParameterMap().get("roundId") == null) return;
      try {
        this.round = this.service.getRound((String)externalContext.getRequestParameterMap().get("roundId"));

        this.images = new ArrayList<Image>(this.round.getImages());
        this.selectedOptions = new ArrayList<Option>(this.round.getOptions());
        readySelectedOptionIds();
      }
      catch (Exception e) {
        e.printStackTrace();
        try {
          externalContext.redirect("/games/xhtml/round/round.xhtml?faces-redirect=true&editError=true");
        }
        catch (IOException e1)
        {
          e1.printStackTrace();
        }
      }
    }
    else {
      this.round = new Round();
      this.showImage = false;
    }
  }

  public void handleFileUpload(FileUploadEvent event)
  {
    Image image = new Image();
    image.setBase64(UploadUtil.getBase64(event.getFile()));
    image.setCreateDate(new Date());

    this.imageService.saveOrUpdate(image);
    if (image.getId() != null) {
      this.images.add(image);
      this.showImage = true;
      FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");

      FacesContext.getCurrentInstance().addMessage(null, msg);
    }
  }

  public List<Option> completeOption(String query)
  {
    if (!(query.trim().equals(""))) {
      this.newOptionName = query;
      return this.optionService.searchOptions(query, this.selectedOptionIds);
    }
    return null;
  }

  public void optionHandelSelect(SelectEvent event)
  {
    this.newOptionName = null;
    Option option = (Option)event.getObject();
    this.selectedOptionIds.add(option.getId());
  }

  public void optionUnSelect(UnselectEvent event)
  {
    this.newOptionName = null;
    Option option = (Option)event.getObject();
    this.selectedOptionIds.remove(option.getId());
  }

  public void saveNewOption()
  {
    if ((null != this.newOptionName) && (!("".equals(this.newOptionName.trim())))) {
      Option option = this.optionService.getOptionByName(this.newOptionName);
      boolean isHave = true;
      if (null == option) {
        option = new Option();
        option.setName(this.newOptionName);
        option.setPinyinName(LanguageUtil.chinese2PinYin(this.newOptionName));
        this.optionService.saveOrUpdate(option);
        isHave = false;
      }
      Set<Option> selectedOptionSet = new HashSet<Option>();
      if (this.selectedOptions != null)
        selectedOptionSet.addAll(this.selectedOptions);
      else {
        this.selectedOptions = new ArrayList<Option>();
      }
      int before = this.selectedOptions.size();

      selectedOptionSet.add(option);
      this.selectedOptions.clear();
      this.selectedOptions.addAll(selectedOptionSet);
      this.selectedOptionIds.add(option.getId());
      int after = this.selectedOptions.size();
      AutoComplete complete = (AutoComplete)findComponent(FacesContext.getCurrentInstance().getViewRoot(), "options");

      if (complete != null) {
        complete.setValue(this.selectedOptions);
      }
      isHave = before == after;
      FacesMessage msg;
      if (!(isHave)) {
        msg = new FacesMessage("������������", this.newOptionName + "������������");

        FacesContext.getCurrentInstance().addMessage(null, msg);
      } else {
        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "������������", this.newOptionName + "������������������������");

        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
      this.newOptionName = null;
    }
    else {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "������������", "������������������������");

      FacesContext.getCurrentInstance().addMessage(null, msg);
    }
  }

  public void showConfirmByD(String id) {
    this.deleteImageId = id;
    RequestContext rc = RequestContext.getCurrentInstance();
    rc.execute("$('#confirmDeleteIDlg').modal();");
  }

  public void deleteImage()
  {
    if (this.deleteImageId != null) {
      Image deleteImage = (Image)this.imageService.get(this.deleteImageId);
      FacesMessage msg;
      if (deleteImage != null) {
        this.imageService.delete(deleteImage);
        this.images.remove(deleteImage);
        msg = new FacesMessage("������������", "������������������");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
      else {
        msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "������������", "������������������������������������");

        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
      RequestContext rc = RequestContext.getCurrentInstance();
      rc.execute("$('#confirmDeleteIDlg').modal('hide');");
    }
  }

  public void saveRound()
  {
    if (!(validate())) return;
    try {
      Set<Image> imageSet = new HashSet<Image>();
      imageSet.addAll(this.images);
      Set<Option> optionSet = new HashSet<Option>();
      optionSet.addAll(this.selectedOptions);
      this.round.setOptions(optionSet);
      if (!(this.edit)) {
        this.round.setCreateDate(new Date());
      }
      this.round.setEditDate(new Date());
      this.service.saveOrUpdate(this.round);
      saveImages();
      showAfterSave();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void saveImages()
  {
    if (null != this.round.getId())
      for (Image image : this.images)
        if (null == image.getRound()) {
          image.setRound(this.round);
          this.imageService.saveOrUpdate(image);
        }
  }

  public void showAfterSave()
  {
    RequestContext rc = RequestContext.getCurrentInstance();
    rc.execute("$('#afterSaveDlg').modal();");
  }

  public void goBack() {
    try {
      FacesContext.getCurrentInstance().getExternalContext().redirect("/games/xhtml/round/round.xhtml?faces-redirect=true");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public boolean validate() {
    String message = null;
    if ((null == this.round.getAnswer()) || (this.round.getAnswer().trim().equals(""))) {
      message = "������������������";
    }
    if ((null == this.selectedOptions) || (this.selectedOptions.size() <= 0)) {
      message = message + ",������������������";
    }
    if ((null == this.images) || (this.images.size() <= 0)) {
      message = message + ",������������������";
    }

    if (null == message) {
      return true;
    }
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "������������", message);

    FacesContext.getCurrentInstance().addMessage(null, msg);
    return false;
  }

  public void clearRound()
  {
    this.round = new Round();
    this.images.clear();
    this.selectedOptions.clear();
    this.selectedOptionIds.clear();
  }

  public void readySelectedOptionIds() {
    for (Option option : this.selectedOptions)
      this.selectedOptionIds.add(option.getId());
  }

  public Round getRound()
  {
    return this.round;
  }

  public void setRound(Round round) {
    this.round = round;
  }

  public List<Option> getSelectedOptions() {
    return this.selectedOptions;
  }

  public void setSelectedOptions(List<Option> selectedOptions) {
    this.selectedOptions = selectedOptions;
  }

  public boolean isShowImage() {
    return this.showImage;
  }

  public void setShowImage(boolean showImage) {
    this.showImage = showImage;
  }

  public List<Image> getImages() {
    return this.images;
  }

  public void setImages(List<Image> images) {
    this.images = images;
  }

  public boolean isEdit() {
    return this.edit;
  }

  public void setEdit(boolean edit) {
    this.edit = edit;
  }
}