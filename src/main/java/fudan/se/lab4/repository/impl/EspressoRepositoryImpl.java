package fudan.se.lab4.repository.impl;

import fudan.se.lab4.constant.FileConstant;
import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.entity.Espresso;
import fudan.se.lab4.repository.EspressoRepository;
import fudan.se.lab4.util.FileUtil;

import java.text.MessageFormat;

public class EspressoRepositoryImpl implements EspressoRepository {
    @Override
    public Espresso getEspresso(String name) {
        return stringArrayToObject(FileUtil.readByName(name, FileConstant.ESPRESSO_CSV));
    }

    @Override
    public void createEspresso(Espresso espresso) {
        FileUtil.write(objectToStringArray(espresso), FileConstant.ESPRESSO_CSV);
    }

    private String[] objectToStringArray(Espresso espresso) {
        // if user already exists, throw exception
        if (FileUtil.exist(espresso.getName(), FileConstant.ESPRESSO_CSV)) {
            throw new RuntimeException(MessageFormat.format(InfoConstant.Entity_EXIST, "Coffee",
                    espresso.getName()));
        }
        String[] array = new String[4];
        array[0] = espresso.getName();
        array[1] = espresso.getDescription();
        array[2] = String.valueOf(espresso.getPrice());
        array[3] = String.valueOf(espresso.getSize());
        return array;
    }

    private Espresso stringArrayToObject(String[] array) {
        Espresso espresso = new Espresso();
        espresso.setName(array[0]);
        espresso.setDescription(array[1]);
        espresso.setPrice(Double.parseDouble(array[2]));
        espresso.setSize(Integer.parseInt(array[3]));
        return espresso;
    }
}