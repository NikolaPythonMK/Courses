package mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TeacherFullNameConverter implements AttributeConverter<TeacherFullname, String> {

    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(TeacherFullname teacherFullname) {
        if(teacherFullname == null){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if(teacherFullname.getName() != null && !teacherFullname.getName().isEmpty()){
            sb.append(teacherFullname.getName()).append(SEPARATOR);
        }

        if(teacherFullname.getSurname() != null && !teacherFullname.getSurname().isEmpty()){
            sb.append(teacherFullname.getSurname()).append(SEPARATOR);
        }

        return sb.toString();
    }

    @Override
    public TeacherFullname convertToEntityAttribute(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }

        String[] pieces = s.split(SEPARATOR);

        if (pieces.length == 0) {
            return null;
        }

        TeacherFullname teacherFullname = new TeacherFullname();

        if(!pieces[0].isEmpty()){
            teacherFullname.setName(pieces[0]);
        }
        if(pieces.length >= 2 &&  !pieces[1].isEmpty()){
            teacherFullname.setSurname(pieces[1]);
        }

        return teacherFullname;
    }
}
