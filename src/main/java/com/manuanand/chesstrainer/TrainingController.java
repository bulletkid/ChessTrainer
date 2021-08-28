package com.manuanand.chesstrainer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller	// This means that this class is a Controller
@RequestMapping(path="/training") // This means URL's start with /training(after Application path)
public class TrainingController {
	@Autowired // This means to get the bean called puzzleRepository
			   // Which is auto-generated by Spring, we will use it to handle the data
	private PuzzleRepository puzzleRepository;

	@Autowired // This means to get the bean called puzzleRepository
			   // Which is auto-generated by Spring, we will use it to handle the data
	private TrainingRepository trainingRepository;

	@Autowired
	private PlayerRepository playerRepository;
	
	///
	// Puzzle Repository
	///
	@PostMapping(path="/create") // Map ONLY POST Requests
	public @ResponseBody Training createTraining (
			@RequestParam Integer playerId, @RequestParam Integer puzzleId) {
		
		// Verify player ID Exists
		Optional<Player> player = playerRepository.findById(playerId);
		if (player.isEmpty()) {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Player with ID" + playerId + " not found");
		}

		// Verify puzzle exists
		Optional<Puzzle> puzzle = puzzleRepository.findById(puzzleId);
		if (puzzle.isEmpty()) {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Puzzle with ID" + puzzleId + " not found");
		}
		
		// Create Training
		Training newTraining = new Training();
		newTraining.setPlayerId(playerId);
		newTraining.setPuzzleId(puzzleId);
		newTraining.setStatus(PuzzleStatus.InProgress);
		
		trainingRepository.save(newTraining);
		
		return newTraining;
	}
	
	@GetMapping(path="/")
	public @ResponseBody Iterable<Training> getAllTrainings() {
		
		// This returns a JSON or XML with the players
		return trainingRepository.findAll();
	}

	@GetMapping(path="/{id}")
	public @ResponseBody Training getSpecificTraining(@PathVariable String id) {
		
		Integer trainingId = null;
		try {
			trainingId = Integer.parseInt(id);
		} catch (NumberFormatException ex) {
			return null;
		}

		Optional<Training> training = trainingRepository.findById(trainingId);
		if (!training.isEmpty()) {
			return training.get();
		} 

		return null;
	}
}