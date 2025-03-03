package com.example.technomakers.springbatch.exercice.config;

import com.example.technomakers.springbatch.exercice.model.Employee;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EmployeeItemWriter extends FlatFileItemWriter<Employee> {

    private static final String HEADER = "Education,JoiningYear,City,PaymentTier,Age,Gender,EverBenched,ExperienceInCurrentDomain,LeaveOrNot";
    private static final String FILE_NAME = "employees_output.csv";

    public EmployeeItemWriter() {
        setResource(new FileSystemResource(FILE_NAME));
        setLineAggregator(new DelimitedLineAggregator<Employee>() {{
            setDelimiter(",");
            setFieldExtractor((FieldExtractor<Employee>) employee -> new Object[]{
                    employee.getEducation(),
                    employee.getJoiningYear(),
                    employee.getCity(),
                    employee.getPaymentTier(),
                    employee.getAge(),
                    employee.getGender(),
                    employee.getEverBenched(),
                    employee.getExperienceInCurrentDomain(),
                    employee.getLeaveOrNot()
            });
        }});
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        super.open(executionContext);
        writeHeader();
    }

    private void writeHeader() {
        Path path = Paths.get(FILE_NAME);
        if (!Files.exists(path) || isFileEmpty(path)) {
            try (Writer writer = Files.newBufferedWriter(path)) {
                writer.write(HEADER);
                writer.write(System.lineSeparator());
            } catch (IOException e) {
                throw new ItemStreamException("Error creating header", e);
            }
        }
    }

    private boolean isFileEmpty(Path path) {
        try {
            return Files.size(path) == 0;
        } catch (IOException e) {
            throw new ItemStreamException("Unable to check if file is empty", e);
        }
    }
}
