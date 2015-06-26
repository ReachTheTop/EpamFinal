package com.epam.project.controller;

import java.util.Calendar;
import java.util.Date;

import com.epam.project.db.model.Event;

public class EventMessage {

	public static void updateEventMessage(Event event) {

		event.setMessage(getEventMassage(event.getDate()));
	}

	public static String getEventMassage(Date dateEvent) {

		String eventMessage = null;

		Date currcentDate = new Date();
		Calendar calendarCurrentDate = Calendar.getInstance();
		calendarCurrentDate.setTime(currcentDate);
		Calendar calendarEventDate = Calendar.getInstance();
		calendarEventDate.setTime(dateEvent);
		
		

		int yearDifference = calendarCurrentDate.get(Calendar.YEAR)
				- calendarEventDate.get(Calendar.YEAR);
		
		
		if (yearDifference > 0) {
			if (yearDifference == 1) {
				return "" + yearDifference + " year ago";
			}
			return "" + yearDifference + " years ago";
		}

		if (yearDifference < 0) {
			if (yearDifference == -1) {
				yearDifference*=-1;
				return "" + yearDifference + " year";
			}
			yearDifference*=-1;
			return "" + yearDifference + " years";
		}

		if (yearDifference == 0) {

			int monthDifference = calendarCurrentDate.get(Calendar.MONTH)
					- calendarEventDate.get(Calendar.MONTH);
			if (monthDifference > 0) {

				if (monthDifference == 1) {
					return "" + monthDifference + " month ago";
				}
				return "" + monthDifference + " months ago";

			}

			if (monthDifference < 0) {

				if (monthDifference == -1) {
					monthDifference*=-1;
					return "" + -monthDifference*(-1) + " month";
				}
				monthDifference*=-1;
				return "" + monthDifference + " months";

			}

			if (monthDifference == 0) {

				int dayDifference = calendarCurrentDate.get(Calendar.DATE)
						- calendarEventDate.get(Calendar.DATE);

				if (dayDifference > 0) {

					if (dayDifference == 1) {
						return "" + dayDifference + " day ago";
					}
					return "" + dayDifference + " days ago";

				}

				if (dayDifference < 0) {

					if (dayDifference == -1) {
						dayDifference*=-1;
						return "" + -dayDifference*(-1) + " day";
					}
					dayDifference*=-1;
					return "" + dayDifference + " days";

				}

				if (dayDifference == 0) {

					int hourDifference = calendarCurrentDate
							.get(Calendar.HOUR_OF_DAY)
							- calendarEventDate.get(Calendar.HOUR_OF_DAY);

					if (hourDifference > 0) {

						if (hourDifference == 1) {
							return "" + hourDifference + " hour ago";
						}
						return "" + hourDifference + " hours ago";

					}

					if (hourDifference < 0) {

						if (hourDifference == -1) {
							hourDifference*=-1;
							return "" + -hourDifference*(-1) + " hour";
						}
						hourDifference*=-1;
						return "" + hourDifference + " hours";

					}

					if (hourDifference == 0) {

						int minuteDifference = calendarCurrentDate
								.get(Calendar.MINUTE)
								- calendarEventDate.get(Calendar.MINUTE);

						if (minuteDifference > 0) {

							if (minuteDifference == 1) {
								return "" + minuteDifference + " minute ago";
							}
							return "" + minuteDifference + " minutes ago";

						}

						if (minuteDifference < 0) {

							if (minuteDifference == -1) {
								minuteDifference*=-1;
								return "" + -minuteDifference*(-1) + " minute";
							}
							minuteDifference*=-1;
							return "" + minuteDifference + " minutes";

						}

						if (minuteDifference == 0) {

							return "Event is now";
						}

					}

				}

			}

		}

		return eventMessage;
	}
}
