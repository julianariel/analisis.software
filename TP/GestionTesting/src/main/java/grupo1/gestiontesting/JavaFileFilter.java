package grupo1.gestiontesting;

import java.io.File;
import java.io.FilenameFilter;

public class JavaFileFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(".java");
	}

}
