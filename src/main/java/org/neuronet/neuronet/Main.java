package org.neuronet.neuronet;

import org.neuronet.neuronet.network.Brain;
import org.neuronet.neuronet.network.Network;
import org.neuronet.neuronet.network.Neuron;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Brain brain = new Brain();

        Neuron sum = new Neuron(true) {
            @Override
            public double execute(double... inputs) {
                double total = 0;
                if (inputs != null && inputs.length > 0) {
                    for (int i = 0; i < inputs.length; i++) {
                        total += inputs[i];
                    }
                }
                return total;
            }
        };

        Neuron powSquare = new Neuron(true) {
            @Override
            public double execute(double... inputs) {
                List<Double> listResults = new ArrayList<>();

                for (int i = 0; i < inputs.length; i++) {
                    listResults.add(Math.pow(inputs[i], 2));
                }
                return listResults.stream().mapToDouble(Double::doubleValue).sum();
            }
        };
        brain.addNeurons(sum, powSquare);

        final double[] inputs = {1,1};
        final List<Network> listNetworkResults = brain.getNetworkFor(4, inputs);

        listNetworkResults.forEach(network -> {
            List<Neuron> listNeurons = network.getListNeurons();

            System.out.println("Network created: "+network.getNetworkId());
            System.out.println("-------------------------------------------");
            listNeurons.forEach(neuron->{
                System.out.println(neuron.toString());
            });
            System.out.println("-------------------------------------------");
            System.out.println("Network result: "+network.getNetworkResult(inputs));
        });

    }
}
