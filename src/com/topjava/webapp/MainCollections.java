package com.topjava.webapp;

import com.topjava.webapp.model.Resume;

import java.util.*;

public class MainCollections {
    private static final String UUID_1 = "uuid1";
    private static final String FULL_NAME_1 = "Aleksei Popov";
    private static final Resume RESUME_1 = new Resume(UUID_1,FULL_NAME_1);

    private static final String UUID_2 = "uuid2";
    private static final String FULL_NAME_2 = "Denis Smirnov";
    private static final Resume RESUME_2 = new Resume(UUID_2, FULL_NAME_2);

    private static final String UUID_3 = "uuid3";
    private static final String FULL_NAME_3 = "Sergei Fedorov";
    private static final Resume RESUME_3 = new Resume(UUID_3, FULL_NAME_3);

    private static final String UUID_4 = "uuid4";
    private static final String FULL_NAME_4 = "Kiril Ponomarev";
    private static final Resume RESUME_4 = new Resume(UUID_4, FULL_NAME_4);

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(RESUME_1);
        collection.add(RESUME_2);
        collection.add(RESUME_3);
        collection.add(RESUME_4);

        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume r = iterator.next();
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID_1)) {
                iterator.remove();
            }
        }
//        System.out.println(collection.toString());

        Map<String, Resume> map = new HashMap<String, Resume>(){
            {
            put(UUID_1, RESUME_1);
            put(UUID_2, RESUME_2);
            put(UUID_3, RESUME_3);
            }
        };

        for (String uuid: map.keySet()) {
            System.out.println(map.get(uuid));
        }

        for (Map.Entry<String, Resume> entry: map.entrySet()) {
            System.out.println(entry.getValue());
        }


        List<Resume> resumes = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        resumes.remove(RESUME_1);
        System.out.println(resumes);
    }
}
