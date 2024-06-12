package com.example.technomakers.springbatch.exercice.config;


import com.example.technomakers.springbatch.exercice.model.Employee;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

public class EmployeeItemReader extends FlatFileItemReader<Employee> {
    public EmployeeItemReader() {
        setResource(new ClassPathResource("employee.csv"));
        setLinesToSkip(1);
        setLineMapper(new DefaultLineMapper<Employee>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("education", "joiningYear", "city", "paymentTier", "age", "gender", "everBenched", "experienceInCurrentDomain", "leaveOrNot");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Employee>() {{
                setTargetType(Employee.class);
            }});
        }});
    }
}
