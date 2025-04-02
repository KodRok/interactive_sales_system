package org.mentor;

import java.util.List;

public interface Adapter {
    List<Order> read(String fileName);

    void write(List<Order> orders);
}
