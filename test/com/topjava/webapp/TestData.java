package com.topjava.webapp;

import com.topjava.webapp.model.ContactsType;
import com.topjava.webapp.model.Resume;
import com.topjava.webapp.model.SectionType;
import com.topjava.webapp.model.sections.AboutOrganization;
import com.topjava.webapp.model.sections.AboutOrganizationSection;
import com.topjava.webapp.model.sections.ListSections;
import com.topjava.webapp.model.sections.TextSection;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestData {
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String FULL_NAME_1 = "Aleksei Popov";
    public static final Resume R1 = new Resume(UUID_1,FULL_NAME_1);

    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String FULL_NAME_2 = "Denis Smirnov";
    public static final Resume R2 = new Resume(UUID_2, FULL_NAME_2);

    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String FULL_NAME_3 = "Sergei Fedorov";
    public static final Resume R3 = new Resume(UUID_3, FULL_NAME_3);

    public static final String UUID_4 = UUID.randomUUID().toString();
    public static final String FULL_NAME_4 = "Kiril Ponomarev";
    public static final Resume R4 = new Resume(UUID_4, FULL_NAME_4);

    static {
        R1.addContact(ContactsType.MAIL, "denis_2000_kolesnik");
        R1.addContact(ContactsType.PHONE, "+79662108956");
        R4.addContact(ContactsType.MAIL, "best_main_in_world");
        R4.addContact(ContactsType.GITHUB, "github.thebeardenis.com");
        List<AboutOrganization.Position> POS1 = new ArrayList<>();
        POS1.add(new AboutOrganization.Position(2005, Month.DECEMBER, 2010, Month.MAY, "position backend gamedev", "create game solo"));
        POS1.add(new AboutOrganization.Position(2001, Month.JANUARY, 2002, Month.DECEMBER, "position2", "content2"));
        List<AboutOrganization.Position> POS2 = new ArrayList<>();
        POS2.add(new AboutOrganization.Position(2014, Month.DECEMBER, 2010, Month.MAY,"Primary school", "PapaRimski"));
        POS2.add(new AboutOrganization.Position(2018, Month.SEPTEMBER, 2025, Month.MAY, "High school", "big student"));
        List<AboutOrganization.Position> POS3 = new ArrayList<>();
        POS3.add(new AboutOrganization.Position(2005, Month.DECEMBER, "All time development", "to now time"));
        R1.addSection(SectionType.PERSONAL, new TextSection("Personal section 1"));
        R1.addSection(SectionType.OBJECTIVE, new TextSection("Objective 1"));
        R1.addSection(SectionType.ACHIEVEMENT, new ListSections("Achievement is big tech skills dev", "school boy"));
        R1.addSection(SectionType.QUALIFICATIONS, new ListSections("Winner big olympics", "Have gold medal with school"));
        R1.addSection(SectionType.EXPERIENCE,
                new AboutOrganizationSection(
                        new AboutOrganization("Organization 1", "https://thebeardenis.git.io", POS1)));
        R1.addSection(SectionType.EDUCATION,
                new AboutOrganizationSection(
                        new AboutOrganization("School number 15",  "https://school15.ru", POS2)));
        R2.addSection(SectionType.EXPERIENCE,
                new AboutOrganizationSection(
                        new AboutOrganization("Organization 14","https://youtube.com", POS3)));

    }
}
