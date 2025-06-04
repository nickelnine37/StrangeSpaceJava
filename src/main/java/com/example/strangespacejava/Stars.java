package com.example.strangespacejava;
import java.util.Random;

public class Stars {
    private final int N;
    private final double[][] positions;  // N rows, 3 columns
    private final Random rand;

    public Stars(int N) {
        this.N = N;
        this.positions = new double[N][3];
        this.rand = new Random();

        initializePositions();
    }

    private double norm(double[] vec) {
        double sum_sq = 0;
        for (int i =0; i < vec.length; i++){
            sum_sq += vec[i] * vec[i];
        }
        return Math.sqrt(sum_sq);
    }

    private void initializePositions() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                positions[i][j] = rand.nextGaussian();  // N(0,1)
            }
            double vec_norm = norm(positions[i]);
            for (int j = 0; j < 3; j++) {
                positions[i][j] /= vec_norm;
            }
        }
    }

    public double[][] getPositions() {
        return positions;
    }

    public int getCount() {
        return N;
    }

    // Optional: get a specific star's coordinates
    public double[] getStar(int index) {
        if (index < 0 || index >= N) {
            throw new IndexOutOfBoundsException("Invalid star index");
        }
        return positions[index];
    }

    // Optional: print the stars
    public void printStars() {
        for (int i = 0; i < N; i++) {
            System.out.printf("Star %d: (%.3f, %.3f, %.3f)%n", i,
                    positions[i][0], positions[i][1], positions[i][2]);
        }
    }
}
