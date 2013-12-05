package model;

import java.io.File;
import java.util.ArrayList;

import cc.mallet.pipe.CharSequence2TokenSequence;
import cc.mallet.pipe.Input2CharSequence;
import cc.mallet.pipe.Pipe;
import cc.mallet.pipe.SerialPipes;
import cc.mallet.pipe.TokenSequence2FeatureSequence;
import cc.mallet.pipe.TokenSequenceLowercase;
import cc.mallet.pipe.TokenSequenceRemoveStopwords;
import cc.mallet.pipe.iterator.FileIterator;
import cc.mallet.topics.ParallelTopicModel;
import cc.mallet.types.InstanceList;

public class MalletLda {
	public static void main(String[] args) throws Exception {
		ArrayList<Pipe> pipeList = new ArrayList<Pipe>();
		pipeList.add(new Input2CharSequence("UTF-8"));
		pipeList.add( new CharSequence2TokenSequence() );
		pipeList.add(new TokenSequenceLowercase());
		pipeList.add( new TokenSequenceRemoveStopwords() );
		pipeList.add( new TokenSequence2FeatureSequence() );
		File directory = new File("C:\\Mallet\\sample-data\\web\\en");
		FileIterator fi = new FileIterator(new File[] {directory},null,FileIterator.STARTING_DIRECTORIES);
		InstanceList instances = new InstanceList (new SerialPipes(pipeList));
		instances.addThruPipe(fi);
		ParallelTopicModel model = new ParallelTopicModel(20, 50.0, 0.01);
		model.addInstances(instances);
		model.setNumThreads(2);
		model.setNumIterations(2000);
		model.estimate();
		model.printTopWords(new File("C:\\Mallet\\turotial_keys_java.txt"), 6, false);
		}
		}
