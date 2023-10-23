import './block.css';

function Block(props) {
    return (
        <section className="block">
            <span className="block-text">{props.temp ? props.temp : "0.00"}{props.postfix}</span>
            <span className="block-title">{props.title}</span>
        </section>
    );
}

export default Block;
