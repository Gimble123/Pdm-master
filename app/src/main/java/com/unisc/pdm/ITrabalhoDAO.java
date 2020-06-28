package com.unisc.pdm;

import java.util.List;

public interface ITrabalhoDAO {

    public boolean salvar(Rgb rgb);
    public List<Rgb> listar();

}
