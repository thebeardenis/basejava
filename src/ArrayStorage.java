/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    void clear() {
        for (Resume element : storage) {
            element = null;
        }
        size = 0;
    }

    void save(Resume r) {
        storage[size++] = r;
    }

    Resume get(String uuid) {
        for (Resume str : storage) {
            if (str != null && str.uuid.equals(uuid)) {
                return str;
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i <= size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[size - 1];
                size--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResumes = new Resume[size];
        System.arraycopy(storage, 0, allResumes, 0, size);
        return allResumes;
    }

    int size() {
        return size;
    }
}
