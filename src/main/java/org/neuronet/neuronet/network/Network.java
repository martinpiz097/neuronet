package org.neuronet.neuronet.network;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Network {
    private final List<Neuron> listNeurons;

    public Network() {
        listNeurons = new ArrayList<>();
    }

    public double getNetworkResult(double... inputs) {
        final AtomicReference<Double> atomicResult = new AtomicReference<>(0.0);
        final List<Neuron> listActive = listNeurons.stream()
                .filter(Neuron::isActive).collect(Collectors.toList());
        if (!listActive.isEmpty()) {
            atomicResult.set(listActive.remove(0).execute(inputs));

            listNeurons.forEach(neuron ->
                atomicResult.set(neuron.execute(atomicResult.get()))
            );
        }
        return atomicResult.get();
    }

    public void addNeuron(Neuron neuron) {
        listNeurons.add(neuron);
    }

    public String getNetworkId() {
        final StringBuilder sbNetwork = new StringBuilder();
        listNeurons.forEach(neuron ->
            sbNetwork.append(neuron.getId()).append('|')
        );
        sbNetwork.deleteCharAt(sbNetwork.length()-1);
        return sbNetwork.toString();
    }
}
