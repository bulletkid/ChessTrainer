package com.manuanand.chesstrainer;

import org.springframework.data.repository.CrudRepository;

import com.manuanand.chesstrainer.Puzzle;

// This will be AUTO IMPLEMENTED by Spring into a Bean called nodeRepository
// CRUD refers Create, Read, Update, Delete

public interface PuzzleRepository extends CrudRepository<Puzzle, Integer> {

}
