import javax.print.attribute.standard.RequestingUserName;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (Resume str:storage) {
            if (str != null) {
                str.uuid = null;
            }
        }
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        for (Resume str: storage) {
            if (str == null) {
                return null;
            } else if (str.uuid.equals(uuid)) {
                return str;
            }
        }
        return null;
    }

    void delete(String uuid) {
        boolean delete = false;
        for (int i=0; i<size; i++) {
            if (storage[i] != null && storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                delete = true;
                break;
            }
        }
        for (int i=0; i<size; i++) {
            if (storage[i] == null) {
                while (i<size) {
                    storage[i] = storage[i+1];
                    storage[i+1] = null;
                    i++;
                }
            }
        }
        if (delete) {
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] res = new Resume[size];
        System.arraycopy(storage, 0, res, 0, size);
        return res;
    }

    int size() {
        return size;
    }
}
