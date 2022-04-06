package org.neuronet.neuronet.network;

public interface Executable {
    boolean isActive();
    double execute(double... inputs);
}
