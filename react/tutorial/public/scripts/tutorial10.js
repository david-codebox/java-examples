/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao [jian-min.gao@hp.com]
 * Date: 2016/2/16
 * Time: 18:29
 */
var CommentList = React.createClass({
    render: function() {
        var commentNodes = this.props.data.map(function(comment) {
            return (
                <Comment author={comment.author} key={comment.id}>
          {comment.text}
                </Comment>
                );
        });
        return (
            <div className="commentList">
        {commentNodes}
            </div>
            );
    }
});
