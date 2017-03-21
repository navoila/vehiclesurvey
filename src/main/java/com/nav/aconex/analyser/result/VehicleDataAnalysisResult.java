package com.nav.aconex.analyser.result;

import com.nav.aconex.analyser.VehicleDataAnalyserType;

import java.util.List;

public class VehicleDataAnalysisResult {
    private String analysisDescription;
    private List<IndividualIntervalAnalysisResult> results;
    private int peakTimeCount;
    private String peakVolumeStartTime;

    public VehicleDataAnalysisResult(String analysisDescription,
                                     List<IndividualIntervalAnalysisResult>
                                               results, int peakTimeCount, String peakVolumeStartTime) {
        this.analysisDescription = analysisDescription;
        this.results = results;
        this.peakTimeCount = peakTimeCount;
        this.peakVolumeStartTime = peakVolumeStartTime;
    }

    public String getAnalysisDescription() {
        return analysisDescription;
    }

    public List<IndividualIntervalAnalysisResult> getResults() {
        return results;
    }

    public int getPeakTimeCount() {
        return peakTimeCount;
    }

    public String getPeakVolumeStartTime() {
        return peakVolumeStartTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
         sb.append("\n\n ============").append(getAnalysisDescription() +
                "============\n" );
        sb.append("\n" + getResults() );
        if(peakTimeCount>0) {
            sb.append("\n\n Peak volumes with count " + getPeakTimeCount());
            sb.append(" starts at '" + getPeakVolumeStartTime());
        }
        return sb.toString();
    }
}
