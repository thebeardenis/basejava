package com.topjava.webapp.storage;

import com.topjava.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {
/*
    private static class ResumeComparator implements Comparator<Resume> {
        @Override
        public int compare(Resume resume, Resume t1) {
            return resume.getUuid().compareTo(t1.getUuid());
        }
    }
 */
    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName);

    @Override
    protected void fillDeleteElement(int index) {
        int countEl = size - index - 1;
        if (countEl > 0) {
            System.arraycopy(storage, index + 1, storage, index, countEl);
        }
    }

    @Override
    protected void insertElement(Resume r, int index) {
        int binaryIndex = -index - 1;
        System.arraycopy(storage, binaryIndex, storage, binaryIndex + 1, size - binaryIndex);
        storage[binaryIndex] = r;
    }

    protected Integer getSearchKey(String fullName) {
        Resume searchKey = new Resume(fullName);
        return Arrays.binarySearch(storage, 0, size, searchKey,RESUME_COMPARATOR);
    }
}
