package org.neuronet.neuronet.network;

import lombok.Data;
import org.neuronet.neuronet.system.NumberUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Brain {
    private final List<Neuron> listNeurons;

    public Brain() {
        this.listNeurons = new ArrayList<>();
    }


    public List<Network> getNetworkFor(double expectedResult, double... inputs) {
        final List<int[]> listPerms = NumberUtil.getPermutationsNoRep(listNeurons.size());
        final List<Integer> listActivationPerms = NumberUtil.getPermutationsRep(new int[]{1, 0}, listNeurons.size());

        return listPerms.stream().map(permArray -> {
            final List<Neuron> neurons = new ArrayList<>();

            Neuron neuron;
            for (int i = 0; i < permArray.length; i++) {
                neuron = listNeurons.get(permArray[i]);
                neurons.add(neuron);
            }
            return new Network(neurons);
        }).filter(network -> network.getNetworkResult(inputs) == expectedResult).collect(Collectors.toList());
    }

    public void addNeurons(Neuron... neurons) {
        if (neurons != null && neurons.length > 0) {
            Neuron neuron;
            for (int i = 0; i < neurons.length; i++) {
                neuron = neurons[i];
                if (neuron != null)
                    listNeurons.add(neuron);
            }
        }
    }

    public void removeNeuron(String id) {
        listNeurons.removeIf(neuron->neuron.getId().equals(id));
    }
}
