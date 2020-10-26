package com.ast.ejecutorws.args;

import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionDef;
import org.kohsuke.args4j.spi.OneArgumentOptionHandler;
import org.kohsuke.args4j.spi.Setter;

import com.google.common.base.Optional;

public class OptionalHandler extends OneArgumentOptionHandler<Optional<String>> {

	public OptionalHandler(CmdLineParser parser, OptionDef option, Setter<? super Optional<String>> setter) {
		super(parser, option, setter);
	}

	@Override
	protected Optional<String> parse(String argument) {		
		return Optional.fromNullable(argument);
	}

	

}
