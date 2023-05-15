package app.viewmodel;

import app.model.Model;
import app.model.User;
import app.shared.Meeting;
import app.shared.Task;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class MeetingViewModel implements PropertyChangeListener
{
  private final Model model;

  private final PropertyChangeSupport support;
  private final ObjectProperty<ObservableList<Meeting>> meetings;

  public MeetingViewModel(Model model){
    this.model = model;
    model.addPropertyChangeListener(this);
    support = new PropertyChangeSupport(this);
    meetings = new SimpleObjectProperty<>();
    meetings.set(FXCollections.observableArrayList(getMeetings()));
  }

  public void addPropertyChangeListener(PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  public void bindMeetings(ObjectProperty<ObservableList<Meeting>> property)
  {
    property.bindBidirectional(meetings);
  }

  public void addMeeting(String title,String description,Date date, Time startTime,Time endTime,String email)
      throws SQLException, RemoteException
  {
    model.addMeeting(title,description,date,startTime,endTime,email);
  }

  public void editMeeting(Meeting oldMeeting, Meeting newMeeting)
      throws SQLException, RemoteException
  {
    model.editMeeting(oldMeeting, newMeeting);
  }

  public void removeMeeting(Meeting meeting)
  {
    model.removeMeeting(meeting);
  }

  public ArrayList<Meeting> getMeetings()
  {
    return model.getMeetings();
  }




  public boolean checkUser()
  {
    return model.checkUser();
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    if(evt.getPropertyName().equals("reloadMeetings"))
    {
      ArrayList<Meeting> list = model.getMeetings();
      ObservableList<Meeting> observableList= FXCollections.observableList(list);
      meetings.set(observableList);
      support.firePropertyChange("reloadMeetings", false, true);
    }
  }
}
