import org.apache.solr.client.solrj.beans.Field;

public class SolrTestBean {
	@Field("id")
	public String id;
	@Field("title")
	public String title;

	public String getId() {
		return id;
	}

	public void setId( String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle( String title) {
		this.title = title;
	}
}
