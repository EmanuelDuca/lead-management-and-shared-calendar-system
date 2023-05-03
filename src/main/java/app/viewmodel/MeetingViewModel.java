package app.viewmodel;

import app.model.Model;
import app.model.User;
import app.shared.Meeting;

import java.util.ArrayList;
import java.util.Date;

public class MeetingViewModel
{
  private Model model;

  public MeetingViewModel(Model model){
    this.model = model;
  }

  public void addMeeting(Date startDate, Date endDate,
      String description, ArrayList<User> employees)
  {
    model.addMeeting(startDate, endDate, description, employees);
  }

  public void removeMeeting(Meeting meeting)
  {
    model.removeMeeting(meeting);
  }

  public ArrayList<Meeting> getMeetings()
  {
    return model.getMeetings();
  }

  public void editMeeting(Date oldStartDate, Date oldEndDate,
      Date startDate, Date endDate, String description,
      ArrayList<User> employees)
  {
    model.editMeeting(oldStartDate, oldEndDate,
        startDate, endDate, description, employees);
  }

}
