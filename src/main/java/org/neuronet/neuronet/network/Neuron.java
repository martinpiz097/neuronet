package org.neuronet.neuronet.network;

import lombok.Data;
import org.neuronet.neuronet.system.TimeManager;

import java.util.UUID;

@Data
public abstract class Neuron implements Executable {
    private final String id;
    private volatile boolean active;

    //private final List<Neuron> prevs;
    //private final List<Neuron> nexts;

    public Neuron() {
        this(TimeManager.getCurrentTime()+"-"+UUID.randomUUID().toString());
    }

    public Neuron(String id) {
        this(id, false);
    }

    public Neuron(boolean active) {
        this(TimeManager.getCurrentTime()+"-"+UUID.randomUUID().toString(), active);
    }

    public Neuron(String id, boolean active) {
        this.id = id;
        this.active = active;
        //prevs = new ArrayList<>();
        //nexts = new ArrayList<>();
    }

    public String toString() {
        return id;
    }
}
