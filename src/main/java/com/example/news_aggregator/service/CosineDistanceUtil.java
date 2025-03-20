package com.example.news_aggregator.service;

/**
 * Utility class to compute cosine distance between two float vectors.
 */

public class CosineDistanceUtil {

    /**
     * Computes the cosine distance between two float vectors.
     * Cosine distance is defined as 1 - cosine similarity.
     *
     * @param a first vector (float array)
     * @param b second vector (float array)
     * @return cosine distance between a and b
     * @throws IllegalArgumentException if the vectors are null or have different lengths.
     */
    public static double compute(float[] a, float[] b) {
        if (a == null || b == null || a.length != b.length) {
            throw new IllegalArgumentException("Vectors must be non-null and have the same length.");
        }
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < a.length; i++) {
            dotProduct += a[i] * b[i];
            normA += a[i] * a[i];
            normB += b[i] * b[i];
        }
        if (normA == 0 || normB == 0) {
            return 1.0;  // maximum distance if one vector is zero
        }
        double cosineSimilarity = dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
        return 1.0 - cosineSimilarity;
    }
}
