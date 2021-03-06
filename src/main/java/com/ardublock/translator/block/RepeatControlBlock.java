package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class RepeatControlBlock extends TranslatorBlock
{

	public RepeatControlBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		String varName="";//this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock teste = this.getRequiredTranslatorBlockAtSocket(0);
		varName=varName+teste.toCode();
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		String ret = "\tfor (" + varName + "= (";
		ret = ret + translatorBlock.toCode() + ") ; " + varName + " <= (";
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(2);
		ret = ret + translatorBlock.toCode() + ") ; "+varName + "+= (";
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(3);
		ret = ret + translatorBlock.toCode() +") )\n\t{\n";
		translatorBlock = getTranslatorBlockAtSocket(4);
		while (translatorBlock != null)
		{
			ret = ret + "\t"+translatorBlock.toCode();
			translatorBlock = translatorBlock.nextTranslatorBlock();
		}
		
		ret = ret + "\t}\n";
		return ret;
	}

}
