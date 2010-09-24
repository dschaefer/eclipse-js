package doug.scripting;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import org.osgi.framework.Bundle;

public class BundleProxyClassLoader extends ClassLoader {

	// TODO make this a list and add an add method
	private Bundle bundle;

	public BundleProxyClassLoader(Bundle bundle) {
		this.bundle = bundle;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Enumeration<URL> findResources(String name) throws IOException {
		return bundle.getResources(name);
	}

	@Override
	protected URL findResource(String name) {
		return bundle.getResource(name);
	}
	
	@Override
	public URL getResource(String name) {
		return findResource(name);
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		return bundle.loadClass(name);
	}
	
	@Override
	protected synchronized Class<?> loadClass(String name, boolean resolve)	throws ClassNotFoundException {
		Class<?> cls = findClass(name);
		if (resolve)
			resolveClass(cls);
		return cls;
	}
	
}
