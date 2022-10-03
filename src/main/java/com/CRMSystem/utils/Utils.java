package com.CRMSystem.utils;

import com.CRMSystem.exceptions.other.InvalidNameException;
import com.CRMSystem.exceptions.other.InvalidTimeException;
import com.CRMSystem.exceptions.strings.InvalidContactsException;
import com.CRMSystem.exceptions.strings.InvalidDatesException;
import com.CRMSystem.models.Contacts;
import com.CRMSystem.models.PIB;
import com.CRMSystem.models.Worker;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Utils {

    public static Map<String, String> getExceptionResponse(Exception e) {
        Map<String,String> map = new HashMap<>();
        map.put("success", "false");
        map.put("error", e.getMessage());
        return map;
    }

    public String processString(String str) {
        return str == null ? null : str.replaceAll("\\s+", " ").trim();
    }

    public boolean isInvalidName(String name) {
        return name == null || name.isEmpty();
    }

    public void checkName(String name) {
        if (isInvalidName(name)) {
            throw new InvalidNameException(Values.INVALID_NAME);
        }
    }

    public void checkDateToBeAfterNow(Date date) {
        if (!date.after(new Date()))
            throw new InvalidTimeException(Values.INVALID_TIME_BEFORE_NOW);
    }

    public static boolean stringsAreEqual(String str1, String str2) {
        if (str1 == null)
            return str2 == null;
        if (str2 == null)
            return false;
        return str1.equals(str2);
    }

    public PIB processPib(PIB pib) {
        return new PIB(processString(pib.getName()),processString(pib.getLastname()),processString(pib.getSurname()));
    }

    public void checkPib(PIB pib) {
        if (isInvalidName(pib.getName()) ||
//                isInvalidName(personName.getLastname()) ||
                isInvalidName(pib.getSurname())) {
            throw new InvalidNameException(Values.INVALID_NAME);
        }
    }

    public Contacts processContacts(Contacts contacts) {
        return new Contacts(processString(contacts.getPhoneNumber()), processString(contacts.getEmail()));
    }

    public boolean isInvalidContacts(Contacts contacts) {
        return contacts.getPhoneNumber() == null || contacts.getEmail() == null ||
                contacts.getPhoneNumber().isEmpty() || contacts.getEmail().isEmpty()
                || isInvalidPhoneNumber(contacts.getPhoneNumber())
                || isInvalidEmail(contacts.getEmail());
    }

    public boolean isInvalidPhoneNumber(String phone) {
        return !phone.matches("[(]?\\d+[)]?[-\\s]?\\d+[-\\s]?\\d+([-\\s]\\d+)?");
    }

    public boolean isInvalidEmail(String email) {
        return !email.matches("[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}");
    }

    public void checkContacts(Contacts contacts) {
        if (isInvalidContacts(contacts)) {
            throw new InvalidContactsException(Values.INVALID_CONTACTS);
        }
    }

    /*public void checkPhoneNumber(String phone) {
        if (isInvalidPhoneNumber(phone)) {
            throw new IncorrectPhoneNumberFormatException(phone);
        }
    }

    public void checkEmail(String email) {
        if (email == null || email.isEmpty()
                || !email.matches("[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}")) {
            throw new InvalidContactsException(Values.INVALID_CONTACTS);
        }
    }
    */

    public void processWorker(Worker worker) {
        worker.setContacts(processContacts(worker.getContacts()));
        worker.setPib(processPib(worker.getPib()));
    }

    public List<Long> stringToListLong(String str) {
        return Arrays.stream(str.split(",")).map(Long::parseLong).collect(Collectors.toList());
    }

    public Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);

        return calendar.getTime();
    }

    /*private boolean isInvalidDateOfBirth(Date dateOfBirth, LocalDate currentDateL) {
        LocalDate dateOfBirthL = convertToLocalDateViaInstant(dateOfBirth);
        return (dateOfBirthL.isAfter(currentDateL.minusYears(Values.MIN_AGE_OF_PERSON_TO_HIRE)) ||
                (dateOfBirthL.isBefore(currentDateL.minusYears(Values.MAX_AGE_OF_PERSON_TO_HIRE))));
    }*/

    private boolean isInvalidDateOfHiring(Date dateOfHiring, LocalDate currentDateL) {
        LocalDate dateOfHiringL = convertToLocalDateViaInstant(dateOfHiring);
        return dateOfHiringL.isBefore(currentDateL.minusYears(Values.MAX_QUANTITY_OF_YEARS_AGO_TO_BE_HIRED));
    }

    private boolean isInvalidDateOfFiring(Date dateOfHiring, LocalDate currentDateL) {
        if (dateOfHiring == null) return false;
        LocalDate dateOfFiringL = convertToLocalDateViaInstant(dateOfHiring);
        return dateOfFiringL.isAfter(currentDateL.plusYears(Values.MAX_QUANTITY_OF_YEARS_AFTER_TO_BE_FIRED));
    }

    public void checkDates(Date startDate, Date endDate) {
        LocalDate currentDate = convertToLocalDateViaInstant(new Date());
        if (isInvalidDateOfHiring(startDate, currentDate) ||
                (endDate != null && !endDate.after(startDate)) ||
                isInvalidDateOfFiring(endDate, currentDate)) {
            throw new InvalidDatesException(Values.INVALID_DATE);
        }
    }

    public Date convertToDateViaSqlTimestamp(LocalDate dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert.atStartOfDay());
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

}
