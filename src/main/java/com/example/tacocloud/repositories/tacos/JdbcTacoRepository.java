package com.example.tacocloud.repositories.tacos;

import com.example.tacocloud.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcTacoRepository implements TacoRepository {
    @Autowired
    private JdbcTemplate jdbc;


    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for(String ingredient : taco.getIngredients()){
            saveIngredientsToTaco(ingredient,tacoId);
        }
        return taco;
    }

    private long saveTacoInfo(Taco taco){
        taco.setPlacedAt(new Date());
        PreparedStatementCreator statement = new PreparedStatementCreatorFactory(
                "insert into Taco(name, createdAt) values (?,?)",
                Types.VARCHAR,
                Types.TIMESTAMP)
                .newPreparedStatementCreator(
                        Arrays.asList(taco.getName(),
                        taco.getPlacedAt().getTime())
                );
        KeyHolder key = new GeneratedKeyHolder();
        jdbc.update(statement,key);
        return key.getKey().longValue();
    }
    private void saveIngredientsToTaco(String ingredient, long tacoId){
        jdbc.update("insert into Taco_Ingredients (taco, ingredient) values (?,?)", tacoId, ingredient);
    }
}
