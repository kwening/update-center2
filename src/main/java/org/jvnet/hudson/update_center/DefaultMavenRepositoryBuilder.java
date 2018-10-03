/**
 * The MIT License
 *
 * Copyright (c) 2011, Jerome Lacoste
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jvnet.hudson.update_center;

import java.net.URL;

import org.apache.maven.artifact.repository.Authentication;
import org.kohsuke.args4j.Option;

public class DefaultMavenRepositoryBuilder {
	
    @Option(name="-repositoryId", usage="Id of the custom maven repository")
    public String repositoryId = "public";
    @Option(name="-repositoryUrl", usage="URL of the custom maven repository")
    public String repositoryUrl = "http://repo.jenkins-ci.org/public/";
    @Option(name="-repositoryUser", usage="Username to access the custom maven repository")
    public String repositoryUser = "";
    @Option(name="-repositoryPass", usage="Password to access the custom maven repository")
    public String repositoryPass = "";
    @Option(name="-downloadUrl", usage="URL of the custom download site")
    public String downloadUrl = "http://updates.jenkins-ci.org/download/";
    
    public DefaultMavenRepositoryBuilder () {}
    
    public static MavenRepositoryImpl getDefaultInstance() throws Exception {
        return new DefaultMavenRepositoryBuilder().getInstance();
    }
    
    public MavenRepositoryImpl getInstance() throws Exception {
        MavenRepositoryImpl instance = new MavenRepositoryImpl(new URL(downloadUrl));
        
        if(!repositoryUser.isEmpty() && !repositoryPass.isEmpty()) {
        	instance.setAuth(new Authentication(repositoryUser,repositoryPass));
        }
        
        instance.addRemoteRepository(repositoryId, new URL(repositoryUrl));
        return instance;
    }
}
