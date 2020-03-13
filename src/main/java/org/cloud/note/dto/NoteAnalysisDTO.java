package org.cloud.note.dto;

import lombok.Data;

import java.util.List;

/**
 * @author wangqianlong
 * @create 2020-03-13 13:36
 */
@Data
public class NoteAnalysisDTO {

    List<String> XTagAnalysis;
    List<String> XShareAnalysis;
    List<String> XCategoryAnalysis;
    List<String> XUserAnalysis;

    List<Integer> YShareAnalysis;
    List<Integer> YCategoryAnalysis;
    List<Integer> YUserAnalysis;


    List<NoteAnalysis> noteTagAnalysis;
}
